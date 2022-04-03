package com.x1.chan.controller;

import com.x1.chan.common.FileUtils;
import com.x1.chan.common.session.SessionConst;
import com.x1.chan.domain.*;
import com.x1.chan.service.BoardService;
import com.x1.chan.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board")
    public String board(Model model, Criteria criteria, HttpServletRequest request) throws NullPointerException {
        int total = boardService.getTotal();
        List<Board> boardList = boardService.boardList(criteria);
        PageMakerDTO pageMaker = new PageMakerDTO(criteria, total);

        model.addAttribute("pageMaker", pageMaker);
        model.addAttribute("boardList", boardList);
        return "board/boardList";
    }

    @GetMapping("/boardForm")
    public String boardForm() {
        return "board/boardForm";
    }

    @PostMapping("/boardForm")
    public String boardWrite(HttpServletRequest request, @RequestParam("contents") String contents, @RequestParam("title") String title) {
        Member loginMember = (Member) request.getSession().getAttribute(SessionConst.LOGIN_MEMBER);
        String naverLoginMember = (String) request.getSession().getAttribute(SessionConst.NAVER_LOGIN_MEMBER);
        if (ObjectUtils.isEmpty(loginMember)) {
            boardService.write(naverLoginMember, contents, title);
            return "redirect:/board";
        }
        boardService.write(loginMember.getLoginId(), contents, title);
        return "redirect:/board";
    }

    @GetMapping("/board/{boardIdx}")
    public String boardView(@PathVariable("boardIdx") Long boardIdx, Model model, HttpServletRequest request) {
        Board boardView = boardService.boardView(boardIdx);
        String filePath = request.getSession().getServletContext().getRealPath("/") + "resources" + File.separator + "upload" + File.separator;

        HttpSession session = request.getSession(false);

        Member loginSession = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if (ObjectUtils.isEmpty(loginSession)) {
            NaverLoginDTO naverSessionId = (NaverLoginDTO) session.getAttribute(SessionConst.NAVER_LOGIN_MEMBER);
            if (naverSessionId.getName().equals(boardView.getLoginId()))
                model.addAttribute("naverMember", naverSessionId);
        } else {
            if (loginSession.getLoginId().equals(boardView.getLoginId()) || loginSession.getUserType().equals("ADMIN"))
                model.addAttribute("loginMember", loginSession);
        }

        model.addAttribute("filePath", filePath);
        model.addAttribute("boardView", boardView);
        return "board/boardView";
    }

    @GetMapping("/board/update/{boardIdx}")
    public String boardUpdateForm(@PathVariable("boardIdx") Long boardIdx, Model model) {
        model.addAttribute("boardView", boardService.boardView(boardIdx));
        return "board/boardUpdateForm";
    }

    @PostMapping("/board/update")
    public String boardUpdate(Board board) {
        boardService.updateBoard(board);
        return "redirect:/board";
    }

    @PostMapping("/board/delete")
    public String boardDelete(@RequestParam("boardIdx") Long boardIdx) {
        boardService.deleteBoard(boardIdx);
        return "redirect:/board";
    }

    @PostMapping("/smartEditor")
    public String smartEditor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //파일정보
        String sFileInfo = "";
        //파일명을 받는다 - 일반 원본파일명
        String filename = request.getHeader("file-name");
        //파일 확장자
        String filename_ext = filename.substring(filename.lastIndexOf(".") + 1);
        //확장자를소문자로 변경
        filename_ext = filename_ext.toLowerCase();
        //이미지 검증 배열변수
        String[] allow_file = { "jpg", "png", "bmp", "gif" };
        System.out.println("파일 이름 가져오기");
        //돌리면서 확장자가 이미지인지
        int cnt = 0;
        for (int i = 0; i < allow_file.length; i++) {
            if (filename_ext.equals(allow_file[i])) {
                cnt++;
            }
        }
        //이미지가 아님
        if (cnt == 0) {
            System.out.println("NOTALLOW_" + filename);
        } else {
        //이미지이므로 신규 파일로 디렉토리 설정 및 업로드
        //파일 기본경로
            String dftFilePath = request.getSession().getServletContext().getRealPath("/");
//파일 기본경로 _ 상세경로
            String filePath = dftFilePath + "resources" + File.separator + "upload" + File.separator;
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            String realFileNm = "";
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String today = formatter.format(new java.util.Date());
            realFileNm = today + UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf("."));
            String rlFileNm = filePath + realFileNm;
        ///////////////// 서버에 파일쓰기 /////////////////
            InputStream is = request.getInputStream();
            OutputStream os = new FileOutputStream(rlFileNm);
            int numRead;
            byte b[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
            while ((numRead = is.read(b, 0, b.length)) != -1) {
                os.write(b, 0, numRead);
            }
            if (is != null) {
                is.close();
            }
            os.flush();
            os.close();
        ///////////////// 서버에 파일쓰기 /////////////////
        // 정보 출력
            sFileInfo += "&bNewLine=true";
            sFileInfo += "&sFileName=" + filename;
            sFileInfo += "&sFileURL=/resources/upload/"+realFileNm;
            System.out.println("sFileInfo + " + sFileInfo);
        }

        return sFileInfo;
    }

    @PostMapping(value = "/smartEditorMultiImageUpload")
    public void smartEditorImageUpload (HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        try {
            Member member = (Member) request.getSession().getAttribute(SessionConst.LOGIN_MEMBER);
            NaverLoginDTO naverLoginDTO = (NaverLoginDTO) request.getSession().getAttribute(SessionConst.NAVER_LOGIN_MEMBER);

            FileVO fileVO = new FileVO();
            FileVO returnFilevo = FileUtils.insertMultipleFile(fileVO, request, response);

            if(ObjectUtils.isEmpty(member)){
                returnFilevo.setLoginId(naverLoginDTO.getName());
            }else{
                returnFilevo.setLoginId(member.getLoginId());
            }
            log.info(returnFilevo.toString());
            boardService.insertFile(returnFilevo);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

