/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pl.openkp.business.walidacja;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public abstract class Walidator {

    @AroundInvoke
    public final Object wykonajWalidację(InvocationContext context) throws Exception {
        WynikWalidacji wynikWalidacji = waliduj(context.getParameters());

        if (wynikWalidacji == null || wynikWalidacji.pusty()) {
            return context.proceed();
        } else {
            return Response.status(Status.NOT_ACCEPTABLE).entity(wynikWalidacji).build();
        }

    }

    protected abstract WynikWalidacji waliduj(Object[] parameters);

}
