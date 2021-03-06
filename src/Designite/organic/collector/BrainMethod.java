package Designite.organic.collector;

import Designite.organic.metrics.MetricName;
import Designite.organic.metrics.Thresholds;
import Designite.organic.resources.Resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Brain Methods tend to centralize the functionality of a class.
 * @author Diego Cedrim
 */
public class BrainMethod extends SmellDetector {
	
	@Override
	public List<Smell> detect(Resource resource) {
		Double halfHighCLOC = Thresholds.getHighThreshold(MetricName.CLOC)/2;
		Double highCC = Thresholds.getHighThreshold(MetricName.CC);
		
		Double mloc = resource.getMetricValue(MetricName.MLOC);
		Double cc = resource.getMetricValue(MetricName.CC);
		Double maxNesting = resource.getMetricValue(MetricName.MaxNesting);
		Double noav = resource.getMetricValue(MetricName.NOAV);
		
		if (mloc > halfHighCLOC && cc > highCC && maxNesting > Thresholds.SEVERAL && noav > Thresholds.MANY) {
			StringBuilder builder = new StringBuilder();
			builder.append("MLOC > " + halfHighCLOC);
			builder.append(", CC > " + highCC);
			builder.append(", MAX_NESTING > " + Thresholds.SEVERAL);
			builder.append(", NOAV > " + Thresholds.MANY);
			
			Smell smell = super.createSmell(resource);
			smell.setReason(builder.toString());
			return Arrays.asList(smell);
		}
		return new ArrayList<>();
	}
	
	@Override
	protected SmellName getSmellName() {
		return SmellName.BrainMethod;
	}

}
