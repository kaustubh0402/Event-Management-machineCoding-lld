interface  Iperson {
    String uniquIdentifier();
    String getFirstName();
    String getLastName();
}

class person implements Iperson{
    private String Id;
    private  String firstName;
    private String lastName;
    String uniuqName ;
    person(String firstName,String lastName){
        this.firstName = firstName;
        this.lastName= lastName;
        uniuqName = firstName+"#"+lastName;
    }

    @Override
    public String uniquIdentifier() {
        return uniuqName;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }
}

interface IEventInfo{
    String getEventName();
    boolean registerPerosn(Iperson ip);
    boolean markAttendance(Iperson ip);
    int getYear();
}

class EventInfo implements  IEventInfo{
    private String eventName;
    private LocalDate date;
    private  int capacity;
    private boolean isCancel;
    private Map<String,Iperson> Registration;
    private Map<String,Iperson> Attendee;
    private int currCapacity ;

    EventInfo(String eventName, LocalDate date,int capacity){
        this.eventName = eventName;
        this.date =date;
        this.capacity = capacity;
        isCancel = false;
        Registration = new HashMap<>();
        Attendee = new HashMap<>();
        currCapacity =0;
    }

    public void setEvent(boolean val){
        isCancel = val;
    }

    public boolean checkCapcity(int val){
        if(val>capacity){
            return false;
        }
        return true;
    }

    public boolean incremetCapcity(int val){
        int newCapacity = currCapacity+val;
        if(checkCapcity(newCapacity)){
            currCapacity= newCapacity;
            return true;
        }
        return  false;
    }

    public boolean alreadyReg(Iperson ip){
        if(Registration.containsKey(ip.uniquIdentifier()))
            return true;
        return false;
    }

    public boolean alreadyAttended(Iperson ip){
        if(Attendee.containsKey(ip.uniquIdentifier()))
            return true;
        return false;
    }

    @Override
    public String getEventName() {
        return eventName;
    }

    @Override
    public boolean registerPerosn(Iperson ip) {
        if(isCancel || alreadyReg(ip) || !checkCapcity(currCapacity+1)){
            return  false;
        }
        Registration.put(ip.uniquIdentifier(),ip);
        incremetCapcity(1);
        return true;
    }

    @Override
    public boolean markAttendance(Iperson ip) {
        if(isCancel || !alreadyReg(ip) || alreadyAttended(ip))
            return false;

        Attendee.put(ip.uniquIdentifier(),ip);
        return true;
    }

    @Override
    public int getYear() {
        return date.getYear();
    }


}

interface IEventManager{
    boolean AddEvent(IEventInfo ev);
    boolean RegisterEvent(String evName, Iperson ip);
    boolean AttendEvent(String evName,Iperson ip);

    List<List<String>> getEventCountByYears();
    List<List<String>> getRegCountByYears();
    List<List<String>> getAttndCountByYears();
}

class EventManager implements IEventManager{
    Map<String,IEventInfo> eventList ;
    Map<Integer,List<IEventInfo>> countYearEvent;

    EventManager(){
        eventList = new HashMap<>();
    }

    public boolean checkEventExist(String evName){
        if(eventList.containsKey(evName))
            return true;
        return false;
    }

    public IEventInfo getEvent(String evName){
        return eventList.get(evName);
    }

    @Override
    public boolean AddEvent(IEventInfo ev) {
        if(checkEventExist(ev.getEventName())){
            return  false;
        }
        eventList.put(ev.getEventName(),ev);
        return true;
    }

    @Override
    public boolean RegisterEvent(String evName, Iperson ip) {
        if(checkEventExist(evName)){
            return  false;
        }

        boolean success= getEvent(evName).registerPerosn(ip);
        return success;
    }

    @Override
    public boolean AttendEvent(String evName, Iperson ip) {
        if(checkEventExist(evName)){
            return  false;
        }

        boolean success= getEvent(evName).markAttendance(ip);
        return success;
    }

    public Map<Integer,List<IEventInfo>> initializeYear(){
        countYearEvent = new TreeMap<>();
        for(IEventInfo curr: eventList.values()){
            int year = curr.getYear();
            if(!countYearEvent.containsKey(year)){
                countYearEvent.put(year,new ArrayList<IEventInfo>());
            }
            countYearEvent.get(year).add(curr);
        }
        return countYearEvent;
    }
    @Override
    public List<> getEventCountByYears() {
        Map<Integer,List<IEventInfo>> eventMap= initializeYear();
        List<> ans = new ArrayList<ArrayList<String>>();
        for(Map.Entry<Integer,List<IEventInfo>> entry : eventMap.entrySet()){
            ans.add(new ArrayList<>(Arrays.asList(entry.getKey().toString(),entry.getValue().size())));
        }
        return ans;
    }

    @Override
    public List<List<String>> getRegCountByYears() {
        return List.of();
    }

    @Override
    public List<List<String>> getAttndCountByYears() {
        return List.of();
    }
}
