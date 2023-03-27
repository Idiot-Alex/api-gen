function formatBytes(bytes) {
  if (bytes <= 0) return '0 B';
  const k = 1024;
  const sizes = ['B', 'KB', 'MB'];
  const i = Math.floor(Math.log(bytes) / Math.log(k));
  const size = sizes[i];
  const value = parseFloat((bytes / Math.pow(k, i)).toFixed(2));
  return `${value} ${size}`;
}

function calcHeight(totalHeight, items=[]) {
  let height = totalHeight;
  items.forEach(item => {
    height -= item;
  })
  return height;
}

export { formatBytes, calcHeight }