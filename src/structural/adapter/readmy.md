Xpay -> Adapter -> PayD

We will create XpayToPayDAdapter an adapter which will be of type PayD, and it wraps an Xpay object 
(the type it supposes to be adapted).
The adapter uses object composition to hold the object, it’s supposed to be
adapting, an Xpay type of object. The object is passed into the adapter through its constructor.