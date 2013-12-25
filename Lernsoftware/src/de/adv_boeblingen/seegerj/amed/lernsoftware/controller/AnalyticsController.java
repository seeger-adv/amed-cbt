package de.adv_boeblingen.seegerj.amed.lernsoftware.controller;

import javax.servlet.http.HttpServletRequest;

import com.dmurph.tracking.AnalyticsConfigData;
import com.dmurph.tracking.JGoogleAnalyticsTracker;
import com.dmurph.tracking.JGoogleAnalyticsTracker.GoogleAnalyticsVersion;

import de.adv_boeblingen.seegerj.amed.lernsoftware.util.Constants;

public class AnalyticsController {
	public static void triggerPageView(HttpServletRequest req) {
		// String page = PathUtil.retrieveAction(req);
		// submitAnalytics(req, page, "view");
	}

	public static void submitAnalytics(HttpServletRequest req, String category, String action) {
		AnalyticsConfigData config = createConfig(req);
		JGoogleAnalyticsTracker tracker = new JGoogleAnalyticsTracker(config, GoogleAnalyticsVersion.V_4_7_2);
		tracker.trackEvent(category, action);
	}

	private static AnalyticsConfigData createConfig(HttpServletRequest req) {
		AnalyticsConfigData config = new AnalyticsConfigData(Constants.ANALYTICS_KEY);
		config.setUserLanguage(req.getLocale().toString());
		config.setUserAgent(req.getHeader("User-Agent"));
		return config;
	}
}
