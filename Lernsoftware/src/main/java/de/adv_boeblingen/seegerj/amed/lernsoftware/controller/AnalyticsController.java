package de.adv_boeblingen.seegerj.amed.lernsoftware.controller;

import javax.servlet.http.HttpServletRequest;

import com.dmurph.tracking.AnalyticsConfigData;
import com.dmurph.tracking.JGoogleAnalyticsTracker;
import com.dmurph.tracking.JGoogleAnalyticsTracker.GoogleAnalyticsVersion;

import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.Constants;
import de.adv_boeblingen.seegerj.amed.lernsoftware.util.PathUtil;

public class AnalyticsController {
	public static void triggerPageView(HttpServletRequest req) {
		String page = PathUtil.retrieveAction(req);
		if (page == null) {
			page = "unknown";
		}
		submitAnalytics(createConfig(req), page, "view");
	}

	public static void submitAnalytics(AnalyticsConfigData config, String category, String action) {
		JGoogleAnalyticsTracker tracker = new JGoogleAnalyticsTracker(config, GoogleAnalyticsVersion.V_4_7_2);
		tracker.trackEvent(category, action);
	}

	private static AnalyticsConfigData createConfig(HttpServletRequest req) {
		AnalyticsConfigData config = new AnalyticsConfigData(Constants.ANALYTICS_KEY);
		config.setUserLanguage(req.getLocale().toString());
		return config;
	}
}
