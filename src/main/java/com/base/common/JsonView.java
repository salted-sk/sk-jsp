package com.base.common;

/**
 * JsonView
 *
 * @author zhangqiao
 * @since 2018-12-31 14:00
 */

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.Map;

public class JsonView extends MappingJackson2JsonView {
    private ObjectMapper objectMapper = null;

    public JsonView() {
        this.loadObjectMapper();
    }

    private void loadObjectMapper() {
        try {
            Field e = MappingJackson2JsonView.class.getDeclaredField("objectMapper");
            e.setAccessible(true);
            this.objectMapper = (ObjectMapper) e.get(this);
        } catch (Exception var2) {
            this.objectMapper = new ObjectMapper();
        }

    }

    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace("Rendering view with name \'" + this.getBeanName() + "\' with model " + model + " and static attributes " + this.getStaticAttributes());
        }

        this.prepareResponse(request, response);
        JsonGenerator generator = this.objectMapper.getJsonFactory().createJsonGenerator(response.getOutputStream(), JsonEncoding.UTF8);
        this.objectMapper.writeValue(generator, model.containsKey("return") ? Response.successful(model.get("return")) : Response.successful(new String()));
    }
}
