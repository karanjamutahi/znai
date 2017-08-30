import React from 'react'
import {isLocalUrl, onLocalUrlClick} from '../structure/links'

const Link = ({url, ...props}) => {
    const isLocal = isLocalUrl(url);
    const onClick = isLocal ? (e) => onLocalUrlClick(e, url) : null
    const targetProp = isLocal ? {} : {target: "_blank"}

    return (
        <a href={url} onClick={onClick} {...targetProp}>
            <props.elementsLibrary.DocElement {...props}/>
        </a>
    )
}

export default Link
