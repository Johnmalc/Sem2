package webservice.jaxws;

@XmlRootElement(name = "reverse", namespace = "http://webservice/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "reverse", namespace = "http://webservice/")
public class Reverse {

    @XmlElement(name = "arg0", namespace = "")
    private String arg0;

    /**
     * @return returns String
     */
    public String getArg0() {
        return this.arg0;
    }

    /**
     * @param arg0 the value for the arg0 property
     */
    public void setArg0(String arg0) {
        this.arg0 = arg0;
    }

}
