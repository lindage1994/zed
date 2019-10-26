package com.example.zed.json;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;

/**
 * @Author: zed
 * @Date: 2019/10/26 19:20
 * @Description: Date Json 序列化
 */
public class JSONDateSerializer implements ObjectSerializer, ObjectDeserializer {
    @Override
    public void write(JSONSerializer jsonSerializer, Object o, Object o1, Type type, int i) throws IOException {
        if (o instanceof Date) {
            Date date = (Date) o;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Time", date.getTime());
            jsonObject.put("Zone", date.getTimezoneOffset());
            jsonObject.put("Year", date.getYear());
            jsonSerializer.write(jsonObject.toString());
        }
    }

    @Override
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object o) {
        final JSONLexer lexer = defaultJSONParser.getLexer();
        JSONObject object = JSONObject.parseObject(lexer.stringVal());
        return (T) object.getDate("Time");
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }
}
