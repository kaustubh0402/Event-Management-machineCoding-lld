interface IEventManager{
    boolean AddEvent(IEventInfo ev);
    boolean RegisterEvent(String evName, Iperson ip);
    boolean AttendEvent(String evName,Iperson ip);

    List<List<String>> getEventCountByYears();
    List<List<String>> getRegCountByYears();
    List<List<String>> getAttndCountByYears();
}
