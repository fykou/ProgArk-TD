package no.ntnu.tdt4240.g25.td.model.entity.factories;

import com.artemis.World;

import no.ntnu.tdt4240.g25.td.model.entity.components.MobComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.PositionComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.RotationComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.TextureComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.VelocityComponent;
import no.ntnu.tdt4240.g25.td.service.AssetService;

public class MobFactory {

    AssetService assetService;

    public MobFactory(AssetService assetService) {
        this.assetService = assetService;
    }

    public void createNormalMob(World world, float x, float y) {
        world.createEntity()
                .edit()
                .add(new TextureComponent(assetService.getTurretRegion(AssetService.TurretRegions.TYPE_2_MK4), -90))
                .add(new PositionComponent(x, y))
                .add(new VelocityComponent(50, 0))
                .add(new RotationComponent())
                .add(new MobComponent(MobComponent.NORMAL_MOB));
    }

}
