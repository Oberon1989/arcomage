package com.Oberon1989;



import org.codehaus.jackson.map.ObjectMapper;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.io.IOException;
import java.io.StringWriter;

public class MessageEncoder implements Encoder.Text<GameState> {
    @Override
    public String encode(GameState gameState) throws EncodeException {
        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(writer, gameState);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
