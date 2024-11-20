package de.metaphoriker.api.event;

import java.util.List;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import de.metaphoriker.api.pathing.filter.PathFilter;
import de.metaphoriker.api.pathing.filter.PathFilterStage;
import de.metaphoriker.api.wrapper.PathPosition;

/** An event called when a Pathfinder starts pathing. */
@Getter
@RequiredArgsConstructor
public class PathingStartFindEvent implements PathingEvent {

  @NonNull private final PathPosition start;
  @NonNull private final PathPosition target;
  @NonNull private final List<PathFilter> filters;
  @NonNull private final List<PathFilterStage> filterStages;
}
