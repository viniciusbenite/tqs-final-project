import React from 'react';
import Barbeiro from './Barbeiro';

export default function CabeleireirosList({barbeiros}) {
    if(barbeiros.length === 0){
        return (
            <div className="empty-search">
                <h3>Não existem cabeleireiros disponíveis</h3>

            </div>
        );
    }
    
    return (
<section className="cabeleireiroslist">
<div className="cabeleireiroslist-center">
    {barbeiros.map(item => {
        return <Barbeiro key={item.id} barbeiro={item} />;
        })}
</div>
</section>

);
}