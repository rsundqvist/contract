package contract.operation;

import contract.assets.Const;
import contract.wrapper.Operation;

import java.util.HashMap;

/**
 * Creates an operation to initialize an {@code AnnotatedVariable}.
 */
public class OP_Message extends Operation {

    /**
     * Version number for this class.
     */
    private static final long serialVersionUID = Const.VERSION_NUMBER;
    private static final OperationType OPERATION = OperationType.message;

    /**
     * Creates a new Init operation. Note that you must set the target, maxSize and
     * initial values.
     */
    public OP_Message () {
        super(OPERATION, new HashMap<Key, Object>(), null, null);
    }

    /**
     * Simply calls setValue().
     *
     * @param message The message to attach to this OP_Message.
     */
    public void setMessage (String message) {
        setValue(message);
    }

    public String getMessage () {
        return getValue();
    }

    /**
     * Set the value(s) with which to initialize this variable.
     *
     * @param value The value(s) with which to initialize this variable.
     */
    public void setValue (String value) {
        body.put(Key.value, value);
    }

    public String getValue () {
        return (String) body.get(Key.value);
    }

    /**
     * Set the halt parameter for this Message. If true, automatic execution will stop
     * once the operation has been run.
     *
     * @param halt The new halt setting.
     */
    public void setHalt (boolean halt) {
        body.put(Key.halt, halt);
    }

    /**
     * Returns the halt value for this Message.
     *
     * @return The halt value for this Message.
     */
    public boolean getHalt () {
        Object halt = body.get(Key.halt);

        if (halt != null && halt instanceof Boolean) {
            return (boolean) halt;
        }

        return false;
    }

    @Override
    public String toString () {
        return getHalt() ? "HALT" : "MESSAGE" + ": \"" + getValue() + "\"";
    }
}
