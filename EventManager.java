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
