package innossh.proto.example.struct;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.google.protobuf.util.Structs;
import com.google.protobuf.util.Values;

public class StructExample {
  public static void main(String[] args) throws InvalidProtocolBufferException {
    final StructExample example = new StructExample();
    System.out.println(example.getJsonFromTextData("text"));
    System.out.println(example.getJsonFromStructData("struct"));
  }

  public String getJsonFromTextData(String name) throws InvalidProtocolBufferException {
    final TextData textData =
        TextData.newBuilder()
            .setName(name)
            .setContent("{\"key1\":\"value1\",\"key2\":2.2}")
            .build();
    return JsonFormat.printer().print(textData);
  }

  public String getJsonFromStructData(String name) throws InvalidProtocolBufferException {
    final StructData structData =
        StructData.newBuilder()
            .setName(name)
            .setContent(Structs.of("key1", Values.of("value1"), "key2", Values.of(2.2)))
            .build();
    return JsonFormat.printer().print(structData);
  }
}
