package no.ntnu.tdt4240.g25.td.model.entity.components.singleton;

import com.artemis.Component;

import net.mostlyoriginal.api.Singleton;

@Singleton
public class BuyUpgradeComponent extends Component {
    public boolean boughtTower1 = false;
    public boolean boughtTower2 = false;
    public boolean upgradedTower = false;
}
