/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.impl;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.Cobol85Parser.DisplayStatementContext;
import io.proleap.cobol.Cobol85Parser.IdentificationDivisionContext;
import io.proleap.cobol.Cobol85Parser.ProcedureDivisionContext;
import io.proleap.cobol.Cobol85Parser.ProgramIdParagraphContext;
import io.proleap.cobol.Cobol85Parser.StopStatementContext;
import io.proleap.cobol.parser.applicationcontext.CobolParserContext;
import io.proleap.cobol.parser.metamodel.CobolScope;
import io.proleap.cobol.parser.metamodel.CobolScopedElement;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.DisplayStatement;
import io.proleap.cobol.parser.metamodel.IdentificationDivision;
import io.proleap.cobol.parser.metamodel.ProcedureDivision;
import io.proleap.cobol.parser.metamodel.ProgramIdParagraph;
import io.proleap.cobol.parser.metamodel.SemanticGraphElement;
import io.proleap.cobol.parser.metamodel.StopStatement;

public abstract class CobolScopeImpl extends CobolScopedElementImpl implements CobolScope {

	private final static Logger LOG = LogManager.getLogger(CobolScopeImpl.class);

	protected final List<CobolScopedElement> scopedElements = new ArrayList<CobolScopedElement>();

	public CobolScopeImpl(final CopyBook copyBook, final CobolScope superScope, final ParseTree ctx) {
		super(copyBook, superScope, ctx);
	}

	@Override
	public DisplayStatement addDisplayStatement(final DisplayStatementContext ctx) {
		DisplayStatement result = (DisplayStatement) getSemanticGraphElement(ctx);

		if (result == null) {
			result = new DisplayStatementImpl(copyBook, this, ctx);

			storeScopedElement(result);
		}

		return result;
	}

	@Override
	public IdentificationDivision addIdentificationDivision(final IdentificationDivisionContext ctx) {
		IdentificationDivision result = (IdentificationDivision) getSemanticGraphElement(ctx);

		if (result == null) {
			result = new IdentificationDivisionImpl(copyBook, this, ctx);

			storeScopedElement(result);
		}

		// program id paragraph
		final ProgramIdParagraph programIdParagraph = addProgramIdParagraph(ctx.programIdParagraph());
		result.setProgramIdParagraph(programIdParagraph);

		return result;
	}

	@Override
	public ProcedureDivision addProcedureDivision(final ProcedureDivisionContext ctx) {
		ProcedureDivision result = (ProcedureDivision) getSemanticGraphElement(ctx);

		if (result == null) {
			result = new ProcedureDivisionImpl(copyBook, this, ctx);

			storeScopedElement(result);
		}

		return result;
	}

	@Override
	public ProgramIdParagraph addProgramIdParagraph(final ProgramIdParagraphContext ctx) {
		ProgramIdParagraph result = (ProgramIdParagraph) getSemanticGraphElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);

			result = new ProgramIdParagraphImpl(name, copyBook, this, ctx);

			storeScopedElement(result);
		}

		return result;
	}

	@Override
	public StopStatement addStopStatement(final StopStatementContext ctx) {
		StopStatement result = (StopStatement) getSemanticGraphElement(ctx);

		if (result == null) {
			result = new StopStatementImpl(copyBook, this, ctx);

			storeScopedElement(result);
		}

		return result;
	}

	protected String determineName(final ParseTree ctx) {
		return CobolParserContext.getInstance().getNameResolver().determineName(ctx);
	}

	protected SemanticGraphElement getSemanticGraphElement(final ParseTree ctx) {
		final SemanticGraphElement result = CobolParserContext.getInstance().getSemanticGraphElementRegistry()
				.getSemanticGraphElement(ctx);
		return result;
	}

	protected void registerSemanticGraphElement(final SemanticGraphElement semanticGraphElement) {
		assert semanticGraphElement != null;
		assert semanticGraphElement.getCtx() != null;

		CobolParserContext.getInstance().getSemanticGraphElementRegistry()
				.addSemanticGraphElement(semanticGraphElement);
	}

	protected void storeScopedElement(final CobolScopedElement scopedElement) {
		assert scopedElement != null;
		assert scopedElement.getCtx() != null;

		registerSemanticGraphElement(scopedElement);

		scopedElements.add(scopedElement);
	}

}
