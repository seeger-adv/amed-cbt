package de.adv_boeblingen.seeegerj.amed.lernoftware.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.boxysystems.jgoogleanalytics.FocusPoint;
import com.boxysystems.jgoogleanalytics.JGoogleAnalyticsTracker;

@WebFilter(urlPatterns = "/*")
public class AnalyticsFilter
		implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException,
			ServletException {

		JGoogleAnalyticsTracker tracker = new JGoogleAnalyticsTracker("Lernsoftware", "UA-46671672-1");
		tracker.trackAsynchronously(new FocusPoint("test"));
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}
