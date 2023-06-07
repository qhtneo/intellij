package com.project.trip.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        System.out.println("ㅋㅋ이거 왜 안됨 ㄹㅇ ㅋㅋ");
        // alert 창을 띄우고 index 페이지로 이동하도록 처리
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('접근권한이 없습니다!'); location.href='/main';</script>");
        out.flush();
    }
}