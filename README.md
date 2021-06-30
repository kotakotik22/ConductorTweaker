### Fork of [Railways](https://github.com/Spirolateral/Railway)

# ConductorTweaker

Adds a ContentTweaker addon for Railways for creating custom conductors

### Example:

```zenscript
#loader contenttweaker

import mods.railways.conductor.ConductorBuilder;

new ConductorBuilder("rich_harris")
    .texture("textures/entity/rich_harris.png")
    .cap().texture("textures/models/armor/svelte_hat.png").build()
    .structureBlock("minecraft:orange_wool")
    .build();
    
    
new ConductorBuilder("bob_ross")
    .texture("textures/entity/bob_ross.png")
    .cap().texture("railways", "textures/models/armor/-color-_golem_hat.png").build()
    .build();
```

![tB5L5ojPkR](https://user-images.githubusercontent.com/61428759/124035878-73f01800-d9fd-11eb-8c33-821f92009c35.gif)
![0EryF06YHh](https://user-images.githubusercontent.com/61428759/124035917-82d6ca80-d9fd-11eb-9c31-b033f5ce634e.gif)
![java_NXROK8OdlR](https://user-images.githubusercontent.com/61428759/124035934-89654200-d9fd-11eb-81ef-328c78373b70.png)


Includes the whole Railways codebase for now, when Railways releases this will use a Gradle dependency
