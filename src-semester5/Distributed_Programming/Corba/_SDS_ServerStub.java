/**
 * _SDS_ServerStub.java
 * Generated by the IDL-to-Java compiler (portable), version "3.0"
 * from SDS_Server.idl
 * Mittwoch, 27. Juni 2001 19.08 Uhr GMT+02:00
 */

public class _SDS_ServerStub extends ObjectImpl implements SDS_Server {
    // Type-specific CORBA::Object operations
    private static String[] __ids = {
            "IDL:SDS_Server:1.0"};

    // Constructors
    // NOTE:  If the default constructor is used, the
    //        object is useless until _set_delegate (...)
    //        is called.
    public _SDS_ServerStub() {
        super();
    }

    public _SDS_ServerStub(Delegate delegate) {
        super();
        _set_delegate(delegate);
    }

    public SDS_ServerPackage.Reply investigate(String name) {
        org.omg.CORBA.portable.InputStream _in = null;
        try {
            org.omg.CORBA.portable.OutputStream _out = _request("investigate", true);
            _out.write_string(name);
            _in = _invoke(_out);
            SDS_ServerPackage.Reply __result = SDS_ServerPackage.ReplyHelper.read(_in);
            return __result;
        } catch (org.omg.CORBA.portable.ApplicationException _ex) {
            _in = _ex.getInputStream();
            String _id = _ex.getId();
            throw new org.omg.CORBA.MARSHAL(_id);
        } catch (org.omg.CORBA.portable.RemarshalException _rm) {
            return investigate(name);
        } finally {
            _releaseReply(_in);
        }
    } // investigate

    public String[] _ids() {
        return (String[]) __ids.clone();
    }

    private void readObject(java.io.ObjectInputStream s) {
        try {
            String str = s.readUTF();
            org.omg.CORBA.Object obj = org.omg.CORBA.ORB.init().string_to_object(str);
            org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate();
            _set_delegate(delegate);
        } catch (java.io.IOException e) {
        }
    }

    private void writeObject(java.io.ObjectOutputStream s) {
        try {
            String str = org.omg.CORBA.ORB.init().object_to_string(this);
            s.writeUTF(str);
        } catch (java.io.IOException e) {
        }
    }
} // class _SDS_ServerStub
