/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2017 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package com.sun.tools.jxc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Yan GAO (gaoyan.gao@oracle.com)
 */
public class SchemaTaskTest extends SchemaAntTaskTestBase {

    private File pkg;
    private File metainf;

    @Override
    public String getBuildScript() {
        return "schemagen.xml";
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        pkg = new File(srcDir, "test");
        metainf = new File(buildDir, "META-INF");
        assertTrue(pkg.mkdirs());
        assertTrue(metainf.mkdirs());
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testFork() throws FileNotFoundException, IOException {
        copy(pkg, "MyTrackingOrder.java", SchemaTaskTest.class.getResourceAsStream("resources/MyTrackingOrder.java_"));
        assertEquals(0, AntExecutor.exec(script, "schemagen-fork"));
    }

    public void testAddmodules() throws IOException {
        copy(pkg, "MyTrackingOrder.java", SchemaTaskTest.class.getResourceAsStream("resources/MyTrackingOrder.java_"));
        assertEquals(0, AntExecutor.exec(script, "schemagen-addmodules"));
    }
}
