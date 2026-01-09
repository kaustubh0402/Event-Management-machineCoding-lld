interface IEventInfo{
    String getEventName();
    boolean registerPerosn(Iperson ip);
    boolean markAttendance(Iperson ip);
    int getYear();
}
