import React from 'react'
import ReservasList from './ReservasList';
import { withReservasConsumer} from '../contextRes';
import Loading from './Loading';

function ReservasContainer ({context}){
    const {loading, sortedReservas} = context;
    if (loading) {
        return <Loading />;
    }
    return (
        <>
            <ReservasList  Reservas={sortedReservas}/>
        </>
    );
}
export default withReservasConsumer(ReservasContainer);
