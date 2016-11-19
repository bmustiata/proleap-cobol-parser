/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.specialnames.impl;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.specialnames.AlphabetClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public abstract class AlphabetClauseImpl extends CobolDivisionElementImpl implements AlphabetClause {

	protected ValueStmt alphabetValueStmt;

	protected final ParseTree ctx;

	public AlphabetClauseImpl(final ProgramUnit programUnit, final ParseTree ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getAlphabetValueStmt() {
		return alphabetValueStmt;
	}

	@Override
	public void setAlphabetValueStmt(final ValueStmt alphabetValueStmt) {
		this.alphabetValueStmt = alphabetValueStmt;
	}

}