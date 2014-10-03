package SDS_ServerPackage;


/**
 * SDS_ServerPackage/ReplyHelper.java
 * Generated by the IDL-to-Java compiler (portable), version "3.0"
 * from SDS_Server.idl
 * Mittwoch, 27. Juni 2001 19.08 Uhr GMT+02:00
 */

abstract public class ReplyHelper {
    private static String _id = "IDL:SDS_Server/Reply:1.0";
    private static org.omg.CORBA.TypeCode __typeCode = null;
    private static boolean __active = false;

    public static void insert(org.omg.CORBA.Any a, SDS_ServerPackage.Reply that) {
        org.omg.CORBA.portable.OutputStream out = a.create_output_stream();
        a.type(type());
        write(out, that);
        a.read_value(out.create_input_stream(), type());
    }

    public static SDS_ServerPackage.Reply extract(org.omg.CORBA.Any a) {
        return read(a.create_input_stream());
    }

    synchronized public static org.omg.CORBA.TypeCode type() {
        if (__typeCode == null) {
            synchronized (org.omg.CORBA.TypeCode.class) {
                if (__typeCode == null) {
                    if (__active) {
                        return org.omg.CORBA.ORB.init().create_recursive_tc(_id);
                    }
                    __active = true;
                    org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember[5];
                    org.omg.CORBA.TypeCode _tcOf_members0 = null;
                    _tcOf_members0 = org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.tk_long);
                    _members0[0] = new org.omg.CORBA.StructMember(
                            "status",
                            _tcOf_members0,
                            null);
                    _tcOf_members0 = org.omg.CORBA.ORB.init().create_string_tc(0);
                    _members0[1] = new org.omg.CORBA.StructMember(
                            "name",
                            _tcOf_members0,
                            null);
                    _tcOf_members0 = org.omg.CORBA.ORB.init().create_string_tc(0);
                    _members0[2] = new org.omg.CORBA.StructMember(
                            "vorname",
                            _tcOf_members0,
                            null);
                    _tcOf_members0 = org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.tk_long);
                    _members0[3] = new org.omg.CORBA.StructMember(
                            "personalnummer",
                            _tcOf_members0,
                            null);
                    _tcOf_members0 = org.omg.CORBA.ORB.init().create_string_tc(0);
                    _members0[4] = new org.omg.CORBA.StructMember(
                            "wohnort",
                            _tcOf_members0,
                            null);
                    __typeCode = org.omg.CORBA.ORB.init().create_struct_tc(SDS_ServerPackage.ReplyHelper.id(), "Reply", _members0);
                    __active = false;
                }
            }
        }
        return __typeCode;
    }

    public static String id() {
        return _id;
    }

    public static SDS_ServerPackage.Reply read(org.omg.CORBA.portable.InputStream istream) {
        SDS_ServerPackage.Reply value = new SDS_ServerPackage.Reply();
        value.status = istream.read_long();
        value.name = istream.read_string();
        value.vorname = istream.read_string();
        value.personalnummer = istream.read_long();
        value.wohnort = istream.read_string();
        return value;
    }

    public static void write(org.omg.CORBA.portable.OutputStream ostream, SDS_ServerPackage.Reply value) {
        ostream.write_long(value.status);
        ostream.write_string(value.name);
        ostream.write_string(value.vorname);
        ostream.write_long(value.personalnummer);
        ostream.write_string(value.wohnort);
    }

}
