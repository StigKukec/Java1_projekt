/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.stig.models;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 *
 * @author natio
 */
public class ActorTransferable implements Transferable {

    private final Actor actor;
    public static final DataFlavor ACTOR_FLAVOR = new DataFlavor(Actor.class, "Actor");
    private static final DataFlavor[] SUPPORTED_FLAVORS = {ACTOR_FLAVOR};

    public ActorTransferable(Actor actor) {
        this.actor = actor;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return SUPPORTED_FLAVORS;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return ACTOR_FLAVOR.equals(flavor);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (isDataFlavorSupported(flavor)) {
            return actor;
        }
        throw new UnsupportedFlavorException(flavor);
    }

}
