/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel;

import java.util.Collection;

public interface Program extends CobolScope {

	CopyBook getCopyBook(String name);

	Collection<CopyBook> getCopyBooks();

	void registerCopyBook(CopyBook copyBook);
}
