import React from 'react'

import { withCabeleireirosConsumer} from '../context';
import Loading from './Loading';



function BarbeirosContainer ({context}){
const {loading, sortedRooms, rooms} = context;

if (loading) {
    return <Loading />;
}
    
}
export default withCabeleireirosConsumer(BarbeirosContainer);



