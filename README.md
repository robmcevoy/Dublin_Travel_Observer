Dublin_Travel_Observer
======================
#### Design

For this assignment I have applied the observer pattern to an aspect of my Final Year Project. The aim of the project is to produce a Real-Time Data Visualisation and Journey Planning Android Application. This assignment focuses on the real-time passenger information (RTPI) for Dublin Bus, Bus Eireann, Luas and Irish Rail. This information is available through a Rest api. In order to get the most update to date information my application will need to query this api frequently and update any relevant visualisations. This is where I have applied the Observer Pattern. 

For this scenario I have created the RTPIObservable which acts as my subject. This object is run as a thread and queries the rest api server every few seconds. RTPIObservable extends Java's superclass Observable and by doing this it gets access to the methods: addObserver(), deleteObserver(), notifyObservers() and setChanged(). I also implemented my own method addStop(). This method takes an object of type Stop adds it to a list of stops. The object Stop encapsulates the concept of a bus/luas stop or train station. The RTPIObservable will find out the due time for the next public transport operator to arrive at all the stops in the stops list. 

For this assignment I have implemented four Observers: DublinBusDisplay, LuasDisplay, BusEireannDisplay and IrishRailDisplay. Each of these four class implements Java's Observer Interface. In their update() method each observer gets the stops relevant to them from the RTPI Observable eg: getBusEireannStops() and simply prints out when the next public transport operator is due at that stop and the stop name. For the final project these will be much more complex visualisations.

Throughout the code a distinction is made on whether a Stop uses 'dublinked' or not. Luas, DublinBus and BusEireann information is gathered through dublinked web services which require authentication and is returned in JSON format. However Irish Rail information is not gathered using dublinked web services and does not need authentication and is returned in XML format. Therefore a distinction is made. 

This assignment is implemented in Java as it is the language used to develop Android applications. 

In the main method four hardcoded Stops are created as no user interface is implemented to allow a user to configure stops. eg: 

```
Stop DublinBusStop = new Stop("1686", "bac", "Castleknock"); 
```
