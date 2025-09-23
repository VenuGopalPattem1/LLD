package ChainOfResponsibility;

public class SwiggyOrder {
    public static void main(String[] args) {
        OrderService o=new OrderValidationHandler(
            new PaymentProcessHandler(
                new OrderConfirmHandler(
                    new OrderPreperationHandler(
                        new OrderDeliveryHandler(null)
                    )
                )
            )
        );
        o.processOrder("Pizza");
    }
}
//Handler
abstract class OrderService{
    protected OrderService service;//inherited to all implementing classes
    public OrderService(OrderService ser){
        this.service=ser;
    }
    public abstract void processOrder(String item);
}

class OrderValidationHandler extends OrderService{
    public OrderValidationHandler(OrderService ser){
        super(ser);
    }

    public void processOrder(String item){
        System.out.println("validating order for "+item);
        if(service!=null){
            service.processOrder(item);
        }
    }
}

class PaymentProcessHandler extends OrderService{
    public PaymentProcessHandler(OrderService ser){
        super(ser);
    }

    public void processOrder(String item){
        System.out.println("PaymentProcessHandler order for "+item);
        if(service!=null){
            service.processOrder(item);
        }
    }
}


class OrderConfirmHandler extends OrderService{
    public OrderConfirmHandler(OrderService ser){
        super(ser);
    }

    public void processOrder(String item){
        System.out.println("OrderConfirmHandler order for "+item);
        if(service!=null){
            service.processOrder(item);
        }
    }
}

class OrderPreperationHandler extends OrderService{
    public OrderPreperationHandler(OrderService ser){
        super(ser);
    }

    public void processOrder(String item){
        System.out.println("OrderPreperationHandler order for "+item);
        if(service!=null){
            service.processOrder(item);
        }
    }
}

class OrderDeliveryHandler extends OrderService{
    public OrderDeliveryHandler(OrderService ser){
        super(ser);
    }

    public void processOrder(String item){
        System.out.println("OrderDeliveryHandler order for "+item);
        if(service!=null){
            service.processOrder(item);
        }
    }
}


