package no.ntnu.tdt4240.g25.td.model.entity.factories;

import com.artemis.BaseSystem;
import com.artemis.annotations.Wire;

import no.ntnu.tdt4240.g25.td.asset.Assets;

public abstract class EntityFactory extends BaseSystem {
    @Override // no processing on factories
    public final void processSystem() {}
}
