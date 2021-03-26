package crcl.vaadin.ui;


import crcl4java.vaadin.client.JogButtonServerRpc;
import crcl4java.vaadin.client.JogButtonState;
import com.vaadin.ui.AbstractComponent;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class JogButton extends AbstractComponent {
    
    
    private final Set<Consumer<AbstractComponent>> mouseDownConsumers = new HashSet<>();
    
    public void addMouseDownConsumer(Consumer<AbstractComponent> consumer) {
        mouseDownConsumers.add(consumer);
    }
    
    public void removeMouseDownConsumer(Consumer<AbstractComponent> consumer) {
        mouseDownConsumers.remove(consumer);
    }
    
    private final Set<Consumer<AbstractComponent>> mouseUpConsumers = new HashSet<>();
    
    public void addMouseUpConsumer(Consumer<AbstractComponent> consumer) {
        mouseUpConsumers.add(consumer);
    }
    
    public void removeMouseUpConsumer(Consumer<AbstractComponent> consumer) {
        mouseUpConsumers.remove(consumer);
    }
    
    private JogButtonServerRpc rpc = new JogButtonServerRpc() {
        private int clickCount = 0;
        

        @Override
        public void mousedown() {
            for(Consumer<AbstractComponent> consumer : mouseDownConsumers) {
                consumer.accept(JogButton.this);
            }
        }

        @Override
        public void mouseup() {
            for(Consumer<AbstractComponent> consumer : mouseUpConsumers) {
                consumer.accept(JogButton.this);
            }
        }
    };
    
    public JogButton() {
        registerRpc(rpc);
    }
    
    public JogButton(String txt) {
        registerRpc(rpc);
        getState().text = txt;
    }
    
    @Override
    public JogButtonState getState() {
        return (JogButtonState) super.getState();
    }
}
