package net.lvcy.fetcher;

import net.lvcy.model.Links;
import net.lvcy.model.Page;

public interface Visitor {

	Links vistPageAndGetNextLinks(Page page);
}
