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
