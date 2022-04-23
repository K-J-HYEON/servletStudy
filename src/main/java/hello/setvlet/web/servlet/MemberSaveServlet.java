package hello.setvlet.web.servlet;

import hello.setvlet.domain.member.Member;
import hello.setvlet.domain.member.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get에 string에서 꺼내든 form 전송방식이든 둘 다 request.getParameter로 보낼 수 있다.
        System.out.println("MemberSaveServlet.service");
        String username = request.getParameter("username");
        // 나이가 request의 getParameter는 항상 문자이다.
        // 폼에서 온 것을 getParameter해서 꺼내온 다음에
        int age = Integer.parseInt(request.getParameter("age"));

        // Member 만들어서 save를 한다.
        Member member = new Member(username, age);
        memberRepository.save(member);


        // 결과를 html로 응답을 해준다.
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter w = response.getWriter();
        // 동적으로 코드가 들어가있다.
        w.write("<html>\n" +
                "<head>\n" +
                " <meta charset=\"UTF-8\">\n" + "</head>\n" +
                "<body>\n" +
                "성공\n" +
                "<ul>\n" +
                "    <li>id="+member.getId()+"</li>\n" +
                "    <li>username="+member.getUsername()+"</li>\n" +
                " <li>age="+member.getAge()+"</li>\n" + "</ul>\n" +
                "<a href=\"/index.html\">메인</a>\n" + "</body>\n" +
                "</html>");
    }
}
