/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.97
 * Generated at: 2024-12-03 07:55:23 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.springproject.domain.Diary;
import com.springproject.domain.Member;
import java.util.List;

public final class diaries_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.LinkedHashSet<>(4);
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.LinkedHashSet<>(4);
    _jspx_imports_classes.add("com.springproject.domain.Member");
    _jspx_imports_classes.add("java.util.List");
    _jspx_imports_classes.add("com.springproject.domain.Diary");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, false, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>전체 다이어리 보기</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("다이어리 입니ㅏㄷ\r\n");

	List<Diary> diaryList = (List<Diary>)request.getAttribute("diaryList");
	HttpSession ssn = request.getSession(false);
	Member mb = (Member)ssn.getAttribute("member");

      out.write("\r\n");
      out.write("\r\n");

if(mb != null)
{
	for(Diary diary : diaryList)
	{

      out.write("\r\n");
      out.write("	<p>========================================= </p>\r\n");
      out.write("	<p>userId : ");
      out.print( mb.getUserId() );
      out.write(" </p>\r\n");
      out.write("	<p>diaryId : ");
      out.print( diary.getDiaryId() );
      out.write(" </p>\r\n");
      out.write("	<p>방문일 : ");
      out.print( diary.getVisit_date() );
      out.write(" </p>\r\n");
      out.write("	<p>메모 : ");
      out.print( diary.getVisit_diary() );
      out.write(" </p>\r\n");
      out.write("	");

		if(diary.getPicture() != null)
		{
			System.out.println("diary에 보여줄 이미지가 있습니다.");
	
      out.write("\r\n");
      out.write("	<p>사진 : </p><img src=\"/howAbout/resources/images/");
      out.print(diary.getPicture().getOriginalFilename());
      out.write("\" style=\"width: 20%\" /> \r\n");
      out.write("	<p> <a href=\"/howAbout/diaries/updateDiary?id=");
      out.print(diary.getDiaryId());
      out.write("\">수정하기</a> | <a href=\"/howAbout/diaries/deleteDiary?id=");
      out.print(diary.getDiaryId());
      out.write("\">삭제하기</a> </p>\r\n");
      out.write("	<br>\r\n");

		}
	}
}
else
{

      out.write("\r\n");
      out.write("	<p>========================================= </p>\r\n");
      out.write("	<p> 로그인 후 아래와 같은 다이어리를 작성해보세요 </p>\r\n");
      out.write("	<p>userId : 홍길동 </p>\r\n");
      out.write("	<p>diaryId : 00000 </p>\r\n");
      out.write("	<p>방문일 : 24.09.09 </p>\r\n");
      out.write("	<p>메모 : 경치가 좋았다 </p>\r\n");
      out.write("	<p>사진 : </p><img src=\"/howAbout/resources/images/dog.png\" style=\"width: 20%\" /> \r\n");
      out.write("	<br>\r\n");

}

      out.write("\r\n");
      out.write("<a href=\"/howAbout/diaries/addDiary\">글쓰기</a>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
