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
