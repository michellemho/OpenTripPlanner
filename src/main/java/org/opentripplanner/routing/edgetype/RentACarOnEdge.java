/* This program is free software: you can redistribute it and/or
 modify it under the terms of the GNU Lesser General Public License
 as published by the Free Software Foundation, either version 3 of
 the License, or (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>. */

package org.opentripplanner.routing.edgetype;

import org.opentripplanner.routing.core.RoutingRequest;
import org.opentripplanner.routing.core.State;
import org.opentripplanner.routing.vertextype.CarRentalStationVertex;

import java.util.Set;

/**
 * Renting a car edge.
 * 
 * Cost is the time to pickup a car plus "inconvenience of renting".
 * 
 * @author Evan Siroky
 * 
 */
public class RentACarOnEdge extends RentACarAbstractEdge {

    private static final long serialVersionUID = 1L;

    public RentACarOnEdge(CarRentalStationVertex from, CarRentalStationVertex to, Set<String> networks) {
        super(from, to, networks);
    }

    @Override
    public State traverse(State s0) {
        RoutingRequest options = s0.getOptions();
        if (options.arriveBy) {
            return super.traverseDropoff(s0);
        } else {
            return super.traverseRent(s0);
        }
    }

    public boolean equals(Object o) {
        if (o instanceof RentACarOnEdge) {
            RentACarOnEdge other = (RentACarOnEdge) o;
            return other.getFromVertex().equals(fromv) && other.getToVertex().equals(tov);
        }
        return false;
    }

    public String toString() {
        return "RentACarOnEdge(" + fromv + " -> " + tov + ")";
    }
}
