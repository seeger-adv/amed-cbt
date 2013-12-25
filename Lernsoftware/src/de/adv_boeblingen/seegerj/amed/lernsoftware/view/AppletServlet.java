package de.adv_boeblingen.seegerj.amed.lernsoftware.view;

import java.io.IOException;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@WebServlet("/Applet/*")
@SuppressWarnings("serial")
public class AppletServlet
		extends HttpServlet {
	private final ClassPool pool = ClassPool.getDefault();

	@Override
	public void init(ServletConfig config) throws ServletException {
		ClassClassPath classPath = new ClassClassPath(getClass());
		pool.insertClassPath(classPath);
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		String className = ((HttpServletRequest) req).getPathInfo().substring(1);
		try {
			CtClass cc = pool.get(className.replace("/", ".").replace(".class", ""));
			res.setContentType("application/x-java-applet;version=1.6.0");
			res.setContentLength(cc.toBytecode().length);
			ServletOutputStream outputStream = res.getOutputStream();
			outputStream.write(cc.toBytecode());
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
