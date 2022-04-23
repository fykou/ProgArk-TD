package no.ntnu.tdt4240.g25.td.model.entity.factories;

import com.artemis.BaseSystem;
import com.artemis.World;
import com.artemis.annotations.Wire;

import no.ntnu.tdt4240.g25.td.service.AssetService;

public abstract class EntityFactory extends BaseSystem {

    @Wire
    protected AssetService assetService;

    @Override // no processing on factories
    public final void processSystem() {}
}
