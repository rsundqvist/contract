package contract.io;

public interface ComListener {

    /**
     * Called when a Cummunicator receives a message.
     *
     * @param messageType
     *            The type of message received.
     */
    public void messageReceived (short messageType);
}
