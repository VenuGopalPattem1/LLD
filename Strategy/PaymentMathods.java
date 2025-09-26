package Strategy;

public class PaymentMathods {
//     Defining a family of algorithms, encapsulating them, and making them interchangeable without altering client
//  code. It's particularly useful in scenarios where you need to switch between multiple algorithms dynamically or
//  add new ones without changing client code.
    public static void main(String[] args) {
        PaymentProcess p=new PaymentProcess();
        Payment credit=new CreditCardPayment();
        Payment debit=new DebitCardPayment();
        Payment phone=new PhonePayPayment();
        p.setPaymentMethod(phone);
        p.Paid(100);
        p.setPaymentMethod(debit);
        p.Paid(1000);
        p.setPaymentMethod(credit);
        p.Paid(1100);
    }
}

interface Payment {
    public void pay(int x);
}

class CreditCardPayment implements Payment{
    @Override
    public void pay(int x) {
        System.out.println(x+" mount is paid by CreditCard");
    }
    
}

class DebitCardPayment implements Payment{
    @Override
    public void pay(int x) {
        System.out.println(x+" mount is paid by DebitCardPayment");
    }
    
}

class PhonePayPayment implements Payment{
    @Override
    public void pay(int x) {
        System.out.println(x+" mount is paid by PhonePayPayment");
    }
    
}

class PaymentProcess{
    private Payment pay;

    public PaymentProcess(){
        pay=null;
    }

    public void setPaymentMethod(Payment p){
        pay=p;
    }
     public void Paid(int val){
        pay.pay(val);
     }
}