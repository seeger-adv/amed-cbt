package de.adv_boeblingen.seegerj.amed.lernsoftware.misc;

import java.util.HashMap;

import javax.servlet.ServletRequest;

@SuppressWarnings("serial")
public class VariableMap
		extends HashMap<String, String> {

	public static VariableMap getMappingFromRequest(ServletRequest req) {
		VariableMap fromRequest = (VariableMap) req.getAttribute("template_variable_mapping");
		if (fromRequest == null) {
			fromRequest = new VariableMap();
			fromRequest.put(Constants.STATUS_PARAM, "");
			req.setAttribute("template_variable_mapping", fromRequest);
		}
		return fromRequest;
	}
}
