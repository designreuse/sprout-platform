package net.savantly.sprout.core.module;

import java.util.List;

public interface ModuleResourceProvider {

	List<String> getCssResources();

	List<String> getJsResources();

}
