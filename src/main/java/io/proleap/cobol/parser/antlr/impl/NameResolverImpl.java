/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.antlr.impl;

import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.Cobol85Parser.ProgramIdParagraphContext;
import io.proleap.cobol.Cobol85Parser.ProgramNameContext;
import io.proleap.cobol.parser.antlr.NameResolver;

public class NameResolverImpl implements NameResolver {

	private final static Logger LOG = LogManager.getLogger(NameResolverImpl.class);

	@Override
	public String determineName(final ParseTree ctx) {
		final String result;

		if (ctx instanceof ProgramIdParagraphContext) {
			result = determineName((ProgramIdParagraphContext) ctx);
		} else if (ctx instanceof ProgramNameContext) {
			result = determineName((ProgramNameContext) ctx);
		} else {
			result = null;
		}

		return result;
	}

	public String determineName(final ProgramIdParagraphContext ctx) {
		final String result = determineName(ctx.programName());
		return result;
	}

	public String determineName(final ProgramNameContext ctx) {
		final String result;

		if (ctx != null) {
			result = ctx.getText();
		} else {
			result = null;
		}

		return result;
	}
}
