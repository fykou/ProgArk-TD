package no.ntnu.tdt4240.g25.td.model.entity.factories;

import com.artemis.World;

import no.ntnu.tdt4240.g25.td.service.AssetService;

public abstract class EntityFactory {

    protected AssetService assetService;
    protected World world;

    public EntityFactory(AssetService assetService) {
        this.assetService = assetService;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
