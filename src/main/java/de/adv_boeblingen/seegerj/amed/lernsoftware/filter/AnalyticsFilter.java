package de.adv_boeblingen.seegerj.amed.lernsoftware.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.dmurph.tracking.AnalyticsConfigData;
import com.dmurph.tracking.JGoogleAnalyticsTracker;
import com.dmurph.tracking.JGoogleAnalyticsTracker.GoogleAnalyticsVersion;

import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.Constants;

public class AnalyticsFilter
implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException,
	ServletException {

		final AnalyticsConfigData analyticsConfig = createConfig(Constants.ANALYTICS_KEY, (HttpServletRequest) req);
		JGoogleAnalyticsTracker tracker = new JGoogleAnalyticsTracker(analyticsConfig, GoogleAnalyticsVersion.V_4_7_2);
		String category = "lesson"; // possible values: lesson, quiz, applet
		String action = "show"; // possible values: show, finished
		tracker.trackEvent(category, action);

		chain.doFilter(req, resp);
	}

	private AnalyticsConfigData createConfig(String analyticsKey, HttpServletRequest req) {
		AnalyticsConfigData config = new AnalyticsConfigData(Constants.ANALYTICS_KEY);
		config.setUserLanguage(req.getLocale().toString());
		return config;
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}
