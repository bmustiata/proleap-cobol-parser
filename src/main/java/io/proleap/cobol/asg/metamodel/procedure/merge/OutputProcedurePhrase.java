/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.merge;

import io.proleap.cobol.Cobol85Parser.MergeOutputThroughContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface OutputProcedurePhrase extends CobolDivisionElement {

	OutputThrough addOutputThrough(MergeOutputThroughContext ctx);

	OutputThrough getOutputThrough();

	Call getProcedureCall();

	void setProcedureCall(Call procedureCall);
}