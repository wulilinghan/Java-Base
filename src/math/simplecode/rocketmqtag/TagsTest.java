package math.simplecode.rocketmqtag;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashMap;
import java.util.Map;

/**
 * Created By gao_e on 2020/3/13 15:44
 */
@Component
@RocketMQMessageListener(topic = "placeOrder", consumerGroup = "data-system-consumer")
class PlaceOrderDataSystemListener implements RocketMQListener<MessageDTO> {
    @Autowired
    private MessageHandler messageHandler;
    @Override
    public void onMessage(MessageDTO rechargeRequest) {
        messageHandler.hand(rechargeRequest);
    }
}
@Component
class MessageHandler {
    @Autowired
    private IBusinessService businessService;
    private static Map<String,EHandler> handlerMap = new HashMap<>(32);
    static {
        handlerMap.put("tag1",EHandler.A_BUSINESS_HANDLER);
        handlerMap.put("tag2",EHandler.B_BUSINESS_HANDLER);
        handlerMap.put("tag3",EHandler.C_BUSINESS_HANDLER);
        // ...
    }
    // 控制跟逻辑分离的编程思想
    public boolean hand(MessageDTO rechargeRequest) {
        String tag = rechargeRequest.getTag();
        EHandler handler = handlerMap.get(tag);
        // 这句代码写在这可以达到复用的目的，写到枚举hand方法中，则需要写多次
        MessageData messageData = handler.getMessageDataFrom(rechargeRequest.getDataJson());
        boolean handResult = handler.hand(messageData, businessService);
        return handResult;
    }
}
interface IBusinessService {
    boolean businessAHand(MessageData messageData);
    boolean businessBHand(MessageData messageData);
    boolean businessCHand(MessageData messageData);
    // ...
}
@Service
class BusinessService implements IBusinessService {
    @Override
    public boolean businessAHand(MessageData messageData) {
        // do
        return true;
    }
    @Override
    public boolean businessBHand(MessageData messageData) {
        // do
        return true;
    }
    @Override
    public boolean businessCHand(MessageData messageData) {
        // do
        return true;
    }
}
enum EHandler {
    A_BUSINESS_HANDLER() {
        @Override
        boolean hand(MessageData messageData, IBusinessService businessService) {
            boolean result = businessService.businessAHand(messageData);
            return result;
        }
        @Override
        MessageData getMessageDataFrom(String dataJson) {
            AMessageData aMessageData = JSONObject.parseObject(dataJson, AMessageData.class);
            return aMessageData;
        }
    },
    B_BUSINESS_HANDLER() {
        @Override
        boolean hand(MessageData messageData, IBusinessService businessService) {
            return businessService.businessBHand(messageData);
        }
        @Override
        MessageData getMessageDataFrom(String dataJson) {
            BMessageData bMessageData = JSONObject.parseObject(dataJson, BMessageData.class);
            return bMessageData;
        }
    },
    C_BUSINESS_HANDLER() {
        @Override
        boolean hand(MessageData messageData, IBusinessService businessService) {
            return businessService.businessCHand(messageData);
        }
        @Override
        MessageData getMessageDataFrom(String dataJson) {
            CMessageData cMessageData = JSONObject.parseObject(dataJson, CMessageData.class);
            return cMessageData;
        }
    };
    abstract boolean hand(MessageData messageData, IBusinessService businessService);
    abstract MessageData getMessageDataFrom(String dataJson);
}
interface MessageData {}
class AMessageData implements MessageData{}
class BMessageData implements MessageData{}
class CMessageData implements MessageData{}
class MessageDTO {
    private String tag;
    private String dataJson;
    // ...
    public String getDataJson() {
        return dataJson;
    }
    public void setDataJson(String dataJson) {
        this.dataJson = dataJson;
    }

    public String getTag() {
        return tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
}
interface RocketMQListener<T> {
    void onMessage(MessageDTO rechargeRequest);
}
@interface Component{}
@interface Service{}
@interface RocketMQMessageListener{
    String topic() default "";
    String consumerGroup() default "";
}